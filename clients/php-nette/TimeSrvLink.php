<?php
namespace Staon\TimeSrv;

use Nette\Http\Url;
use Nette\Utils\Json;

/**
 * Link object to the time source service
 */
class TimeSrvLink
{
    /** @var Url */
    private $url;
    /** @var \GuzzleHttp\Client */
    private $http;

    /**
     * ID of a group which returns current real time
     */
    public const REALTIME_GROUP = "realtime";

    /**
     * Ctor
     *
     * @param Url $url URL of the time source service
     */
    public function __construct(Url $url)
    {
        $this->url = $url;

        /* -- create the HTTP client */
        $this->http = new \GuzzleHttp\Client([
            'base_uri' => $this->url->getAbsoluteUrl()
        ]);
    }

    /**
     * Factory method constructing the object from a string URL
     */
    public static function fromStringUrl(string $url): TimeSrvLink
    {
        return new TimeSrvLink(new Url($url));
    }

    /**
     * Set current time for specified time group
     *
     * @param string $group Name of the group (it must not contain any slash)
     * @param int $seconds Seconds since the UNIX epoch
     * @param int $microseconds Microseconds in current second
     * @throws \Exception
     */
    public function setTime(
        string $group,
        int $seconds,
        int $microseconds = 0): void
    {
        $response = $this->http->post('time/' . $group, [
            \GuzzleHttp\RequestOptions::JSON => [
                'seconds' => $seconds,
                'micro_seconds' => $microseconds
            ]
        ]);
        if ($response->getStatusCode() != 200) {
            throw new \Exception($response->getReasonPhrase(), $response->getStatusCode());
        }
    }

    /**
     * Get current time from a time group
     *
     * @param string $group Name of the group
     * @return array A pair (seconds, microseconds)
     * @throws \Exception
     */
    public function getTime(string $group): array
    {
        $response = $this->http->get('time/' . $group);
        if ($response->getStatusCode() != 200) {
            throw new \Exception($response->getReasonPhrase(), $response->getStatusCode());
        }

        /* -- parse the returned JSON */
        $parsed = Json::decode((string)$response->getBody());
        return [$parsed->seconds, $parsed->micro_seconds];
    }

    /**
     * Get current time
     * @throws \Exception
     */
    public function getTimeDt(string $group): \DateTimeImmutable
    {
        [$seconds, $microseconds] = $this->getTime($group);
        $dt = new \DateTimeImmutable();
        return $dt->setTimestamp($seconds);
        /* -- TODO: set the microseconds */
    }
}
